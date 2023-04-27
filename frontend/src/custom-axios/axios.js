import axios from "axios";

const instance = axios.create({
    baseURL:'http://localhost:9091/api',
    headers: {
        'Access-Control-Allow-Origin' : '*',
        "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS"
    },
    withCredentials:false
    // validateStatus:200
})

export default instance;