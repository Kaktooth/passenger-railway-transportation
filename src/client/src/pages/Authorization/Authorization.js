import { Alert, Button, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { SiTrainerroad } from "react-icons/si";
import { NavLink, useNavigate } from "react-router-dom";
import { loginUser } from "../../services/authorization";

import styles from "./styles/Authorization.module.css";

function Authorization() {
	const navigate = useNavigate();

	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");

	const [errors, setErrors] = useState({ email: "", password: "", description: "" });

	useEffect(() => {
		localStorage.setItem("authorized", "0");
		localStorage.removeItem("email");
	}, []);
	useEffect(() => setErrors({ email: "", password: "", description: "" }), [email, password]);

	const onSignIn = async () => {
		await loginUser({ email, password }).then(res => {
			const { response, token, email } = res;

			if (token && email) {
				navigate("/stations");
				localStorage.setItem("email", email);
				localStorage.setItem("authorized", "1");
				setErrors({ email: "", password: "", description: "" });
			} else if (!response) {
				setErrors({ ...errors, description: "Service Unavailable" });
			} else if (response.status === 400) {
				const { email, password } = response.data.details;
				setErrors({ ...errors, email: email || "", password: password || "" });
			} else if (response.status === 401 || response.status === 500) {
				const { description } = response.data;
				setErrors({ ...errors, description: description || "" });
			}
		});
	};

	const renderTextField = (value, onChange, label, type, isRequired, error, helperText) => {
		return (
			<TextField
				value={value}
				onChange={event => onChange(event.target.value)}
				className={styles.form_body_input}
				label={label}
				type={type}
				variant="outlined"
				required={isRequired}
				error={!!error}
				helperText={helperText}
			/>
		);
	};

	return (
		<div className={styles.wrapper}>
			<div className={styles.form}>
				<div className={styles.form_header}>
					<SiTrainerroad color="#0026ff" size={48} />
					<h4 className={styles.form_header_text}>Sign in</h4>
				</div>
				<div className={styles.form_body}>
					{renderTextField(email, setEmail, "Email", "email", true, !!errors.email, errors.email)}
					{renderTextField(password, setPassword, "Password", "password", true, !!errors.password, errors.password)}
					<NavLink className={styles.form_body_button} to={"/stations"}>
						<Button
							onClick={e => {
								e.preventDefault();
								onSignIn();
							}}
							className={styles.form_body_button}
							variant="contained">
							Sign in
						</Button>
					</NavLink>
					{errors.description && (
						<Alert className={styles.errorAlert} severity="error">
							{errors.description}
						</Alert>
					)}
				</div>
			</div>
		</div>
	);
}

export default Authorization;
