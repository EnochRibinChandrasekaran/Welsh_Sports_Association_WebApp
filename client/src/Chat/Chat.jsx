import React, { useEffect, useState } from 'react';
import { over } from 'stompjs';
import SockJS from 'sockjs-client';
import axios from "axios";
import { BASE_URL } from "../apiConfig";
var stompClient = null;
//References : https://github.com/sockjs/sockjs-client
const Chat = ({ id, type }) => {
    const [privateChats, setPrivateChats] = useState(new Map());
    const [publicChats, setPublicChats] = useState([]);
    const [tab, setTab] = useState("CHATROOM");
    const [userData, setUserData] = useState({
        username: '', 
        receivername: '',
        connected: false,
        message: ''
    });

    useEffect(() => {
        const fetchData = async () => {
            try {
                let response;
                if (type === "organiser") {
                    response = await axios.get(`${BASE_URL}/api/organiser/${id}`);
                    setUserData(prevUserData => ({
                        ...prevUserData,
                        username: response.data.companyName
                    }));
                } else {
                    response = await axios.get(`${BASE_URL}/api/volunteer/${id}`);
                    console.log(response.data);
                    setUserData(prevUserData => ({
                        ...prevUserData,
                        username: response.data.firstName
                    }));
                }
            } catch (error) {
                console.error("Error fetching user data:", error);
            }
        };

        if (id && type) {
            fetchData();
        }
    }, [id, type]);

    useEffect(() => {
        if (userData.username) {
            connect();
        }
    }, [userData.username]);

    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    };

    const onConnected = () => {
        setUserData(prevUserData => ({ ...prevUserData, connected: true }));
        stompClient.subscribe('/chatroom', onMessageReceived);
        stompClient.subscribe(`/user/${userData.username}/private`, onPrivateMessage);
        userJoin();
    };

    const userJoin = () => {
        var chatMessage = {
            senderName: userData.username,
            status: "JOIN"
        };
        stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
    };

    const onMessageReceived = (payload) => {
        var payloadData = JSON.parse(payload.body);
        switch (payloadData.status) {
            case "JOIN":
                if (!privateChats.get(payloadData.senderName)) {
                    privateChats.set(payloadData.senderName, []);
                    setPrivateChats(new Map(privateChats));
                }
                break;
            case "MESSAGE":
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
            default:
                break;
        }
    };

    const onPrivateMessage = (payload) => {
        var payloadData = JSON.parse(payload.body);
        if (privateChats.get(payloadData.senderName)) {
            privateChats.get(payloadData.senderName).push(payloadData);
            setPrivateChats(new Map(privateChats));
        } else {
            let list = [];
            list.push(payloadData);
            privateChats.set(payloadData.senderName, list);
            setPrivateChats(new Map(privateChats));
        }
    };

    const onError = (err) => {
        console.log(err);
    };

    const handleMessage = (event) => {
        const { value } = event.target;
        setUserData({ ...userData, message: value });
    };

    const sendValue = () => {
        if (stompClient) {
            var chatMessage = {
                senderName: userData.username,
                message: userData.message,
                status: "MESSAGE"
            };
            stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
            setUserData({ ...userData, message: "" });
        }
    };

    return (
        <div className="relative poppins-regular">
            {userData.connected ? (
                <div className="flex flex-row m-10 p-5 h-[600px]">
                    <div className="w-1/5">
                        <ul className="list-none">
                            <li onClick={() => setTab("CHATROOM")} className={`p-2 bg-gray-200 shadow-md my-1 cursor-pointer ${tab === "CHATROOM" && "bg-red-500 text-white"}`}>Chatroom</li>
                            {[...privateChats.keys()].map((name, index) => (
                                <li onClick={() => setTab(name)} className={`p-2 bg-gray-200 shadow-md my-1 cursor-pointer ${tab === name && "bg-red-500 text-white"}`} key={index}>{name}</li>
                            ))}
                        </ul>
                    </div>
                    <div className="w-4/5 ml-3">
                        <div className="h-[80%] border border-red-300 overflow-y-auto">
                            <ul className="list-none">
                                {publicChats.map((chat, index) => (
                                    <li className={`flex flex-row p-2 m-2 shadow-md ${chat.senderName === userData.username ? "justify-end" : ""}`} key={index}>
                                        {chat.senderName !== userData.username && (
                                            <div className="bg-red-400 text-black px-2 py-1 rounded">{chat.senderName}</div>
                                        )}
                                        <div className="ml-2">{chat.message}</div>
                                        {chat.senderName === userData.username && (
                                            <div className="bg-orange-400 text-black px-2 py-1 rounded">{chat.senderName}</div>
                                        )}
                                    </li>
                                ))}
                            </ul>
                        </div>

                        <div className="flex flex-row mt-2">
                            <input type="text" className="w-9/12 p-2 mx-2" placeholder="Enter the message" value={userData.message} onChange={handleMessage} />
                            <button type="button" className="flex items-center justify-center bg-gray-200 hover:bg-white px-4 py-2 rounded-md text-sm tracking-wide transition-all border-2 border-rose-700 space-x-2 font-semibold md:w-auto w-1/2 " onClick={sendValue}>Send</button>
                        </div>
                    </div>
                </div>
            ) : (
                <div data-testid="chat">Connecting...</div>
            )}
        </div>
    );
};

export default Chat;
