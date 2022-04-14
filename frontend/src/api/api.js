import axios from "axios";

let headers = {
    "Access-Control-Allow-Origin": "http://localhost:8080",
};

export let axiosApi = axios.create({
    baseURL: `http://localhost:8080/api`,
    headers: headers,
});