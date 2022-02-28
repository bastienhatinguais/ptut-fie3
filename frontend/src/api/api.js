import axios from "axios";

export const axiosApi = axios.create({
  baseURL: `api`,
  headers: {
    "Access-Control-Allow-Origin": "http://localhost:8080",
  },
});
