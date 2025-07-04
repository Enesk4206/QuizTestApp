import axios from "axios";

const instance = axios.create({
    baseURL:'http://localhost:8080/api',
    headers:{
        'Content-Type':'application/json',
    },
});

instance.interceptors.request.use(
    (config) => {
        const user = JSON.parse(localStorage.getItem('user'));
        if(user?.token){
            config.headers.Authorization = `Bearer ${user.token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default instance;