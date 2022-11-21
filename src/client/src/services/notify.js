import axios from "axios";

const baseURL = "https://passenger-railway-transportati.herokuapp.com/api";

// Send message
export const sendMessage = async data => {
	const res = await axios.post(`${baseURL}/operator/sendMessage`, data);
	console.log(res);
	return res.data;
};
