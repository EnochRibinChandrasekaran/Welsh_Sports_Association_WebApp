variable "flavor" {
  default = "m1.medium"
}

variable "image" {
  default = "Rocky Linux 9 20220830"
}

variable "name" {
  default = "clientInstance"
}

variable "network" {
  default = "username_network"
}

variable "pool" {
  default = "cscloud_private_floating"
}

variable "keypair" {
  default = "openstack"
}

variable "floating_ip" {
  default = "10.72.100.140"
}

variable "server_script" {
  default = "./serverInstanceScript.sh"
}

variable "security_description" {
  default = "Terraform security group"
}

variable "security_name" {
  default = "openstack2"
}