#!/usr/bin/bash

set -e  # Exit on error

# Update logging configuration
echo "Updating logging configuration..."
sudo sh -c "echo '*.info;mail.none;authpriv.none;cron.none /dev/ttyS0' >> /etc/rsyslog.conf"
sudo systemctl restart rsyslog

# Display user and directory information
echo "Logged in as $USER."
echo "In directory $PWD"

# Allow SSL certificate
echo "Establishing setools certificate..."
sudo dnf install -y setools policycoreutils-python-utils
ausearch -c 'mandb' --raw | audit2allow -M my-mandb
sudo semodule -X 300 -i my-mandb.pp

# Install MariaDB if not already installed
if ! rpm -q mariadb-server > /dev/null; then
    echo "Installing MariaDB..."
    sudo dnf install mariadb-server -y
    sudo systemctl start mariadb
    sudo systemctl status mariadb
    sudo systemctl enable mariadb

    # Create mysql_secure_installation.txt for automated setup
    echo "Creating mysql_secure_installation.txt..."
    cat << EOF > mysql_secure_installation.txt
n
Y
comsc
comsc
Y
Y
Y
Y
Y
EOF

    # Run mysql_secure_installation with automated inputs
    echo "Running mysql_secure_installation..."
    sudo mysql_secure_installation < mysql_secure_installation.txt
fi

# Install Git, Nginx, and other necessary packages
echo "Installing additional packages..."
sudo dnf install -y git nginx java-17-openjdk-devel wget unzip nodejs conntrack

# Set up Java 17 environment
echo "Setting up Java 17 environment..."
export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
export PATH=$JAVA_HOME/bin:$PATH

# Configure Git SSH key for GitLab
echo "Configuring Git SSH key..."
mkdir -p /root/.ssh
touch /root/.ssh/known_hosts
ssh-keyscan git.cardiff.ac.uk >> /root/.ssh/known_hosts
chmod 644 /root/.ssh/known_hosts

# Clone the repository
echo "Cloning Git repository..."
cd /home/rocky
cat << EOF > gitlab-keypair.key
-----BEGIN OPENSSH PRIVATE KEY-----
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
-----END OPENSSH PRIVATE KEY-----
EOF
chmod 400 gitlab-keypair.key
sudo chown rocky:rocky gitlab-keypair.key
eval $(ssh-agent)
ssh-add gitlab-keypair.key

# Clone the repository
echo "Cloning Git repository..."
git clone git@git.cardiff.ac.uk:c23028822/welsh-sports-association-volunteer-app.git

# Build the database
echo "Building database..."
cd welsh-sports-association-volunteer-app
sudo git checkout origin/devops_implementation

cd server
mysql -u root -pcomsc < src/main/resources/schema.sql
mysql -u root -pcomsc < src/main/resources/data.sql

# Install and configure Gradle
echo "Installing Gradle..."
wget https://services.gradle.org/distributions/gradle-7.6-bin.zip
sudo mkdir -p /opt/gradle
sudo unzip -d /opt/gradle gradle-7.6-bin.zip
export PATH=$PATH:/opt/gradle/gradle-7.6/bin
echo "Gradle version:"
gradle -v

# Initialize and build the Gradle project
echo "Initializing and building Gradle project..."
gradle init || true
gradle clean
gradle build -x test
gradle bootRun