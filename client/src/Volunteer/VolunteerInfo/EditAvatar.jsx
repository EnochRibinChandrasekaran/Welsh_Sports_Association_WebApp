import React, { useState, useRef, useEffect } from "react";
import AvatarEditor from "react-avatar-editor";
import { IoIosCamera } from "react-icons/io";
import {
  Button,
  Box,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Slider,
  IconButton,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import AvatarImage from "../../assets/avatar.png";
import axios from "axios";

const EditAvatar = ({
  edit,
  submitURL,
  setEditAvatarError,
  setEditAvatarSuccess,
  onUpdate,
}) => {
  const [image, setImage] = useState(AvatarImage);
  const [croppedImage, setCroppedImage] = useState("");
  const [open, setOpen] = useState(false);
  const [scale, setScale] = useState(1);
  const editorRef = useRef(null);

  //load image
  useEffect(() => {
    const fetchImage = async () => {
      try {
        const response = await axios.get(`${submitURL}`, {
          responseType: "blob",
        });
        const imageBlob = URL.createObjectURL(response.data);
        setImage(imageBlob);
      } catch (error) {
        console.error("Error fetching options : ", error);
      }
    };
    fetchImage();
  }, []);
  const handleNewImage = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImage(file);
    }
  };

  //save image
  const handleSave = async () => {
    if (editorRef.current) {
      const canvas = editorRef.current.getImageScaledToCanvas();
      const base64Image = canvas.toDataURL();
      const response = await axios.post(`${submitURL}`, {
        profileImage: base64Image,
      });
      setCroppedImage(base64Image);

      if (response.status === 200) {
        setEditAvatarError(false);
        setEditAvatarSuccess(true);
      } else {
        setEditAvatarError(true);
        setEditAvatarSuccess(false);
      }

      handleClose();
    }
    onUpdate();
  };

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handleScaleChange = (event, newValue) => setScale(newValue);

  return (
    <Box
      display="flex"
      justifyContent="center"
      alignItems="center"
      sx={{ p: 2 }}
    >
      <Box position="relative" textAlign="center">
        <img
          src={croppedImage || image}
          alt="Cropped"
          style={{ width: 100, height: 100, borderRadius: "50%" }}
        />

        <IconButton
          hidden={!edit}
          onClick={handleOpen}
          sx={{
            position: "absolute",
            bottom: -10,
            right: -10,
            backgroundColor: "#e0e0e0",
            "&:hover": { backgroundColor: "grey.300" },
          }}
        >
          <IoIosCamera size={24} color="#cc1b00" />
        </IconButton>
      </Box>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">
          {" "}
          Edit Profile Picture
          <IconButton
            aria-label="close"
            onClick={handleClose}
            sx={{ position: "absolute", right: 8, top: 8 }}
          >
            <CloseIcon />
          </IconButton>
        </DialogTitle>
        <DialogContent>
          <AvatarEditor
            ref={editorRef}
            image={croppedImage || image}
            width={250}
            height={250}
            border={50}
            borderRadius={125}
            color={[255, 255, 255, 0.6]} // RGBA
            scale={scale}
            rotate={0}
          />
          <Button variant="outlined" color="error" component="label">
            Upload Image
            <input
              type="file"
              hidden
              onChange={handleNewImage}
              accept="image/*"
            />
          </Button>
          <Slider
            value={scale}
            min={1}
            max={2}
            step={0.01}
            onChange={handleScaleChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => handleSave()} color="error">
            Save changes
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default EditAvatar;
