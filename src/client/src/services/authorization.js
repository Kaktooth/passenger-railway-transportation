import axios from "axios";

const baseURL = "https://passenger-railway-transportati.herokuapp.com/api";

// Login user
export const loginUser = userData => {
	return axios
		.post(`${baseURL}/user/login`, userData)
		.then(res => res.data)
		.catch(error => error);
};
