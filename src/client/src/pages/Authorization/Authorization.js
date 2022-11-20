import { Alert, Button, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { SiTrainerroad } from "react-icons/si";
import { NavLink } from "react-router-dom";
import { loginUser } from "../../services/authorization";

import styles from "./styles/Authorization.module.css";

function Authorization() {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");

	const [errors, setErrors] = useState({ email: "", password: "", description: "" });

	useEffect(() => localStorage.setItem("authorized", "0"), []);
	useEffect(() => setErrors({ email: "", password: "", description: "" }), [email, password]);

	const onSignIn = async () => {
		await loginUser({ email, password }).then(res => {
			const { response } = res;
			if (response.status === 400) {
				const { email, password } = response.data.details;
				setErrors({ ...errors, email: email || "", password: password || "" });
			}

			if (response.status === 401) {
				const { description } = response.data;
				setErrors({ ...errors, description: description || "" });
			}
		});
	};

	return (
		<div className={styles.wrapper}>
			<div className={styles.form}>
				<div className={styles.form_header}>
					<SiTrainerroad color="#0026ff" size={48} />
					<h4 className={styles.form_header_text}>Sign in</h4>
				</div>
				<div className={styles.form_body}>
					<TextField
						value={email}
						onChange={event => setEmail(event.target.value)}
						className={styles.form_body_input}
						label="Email"
						variant="outlined"
						required
						error={!!errors.email}
						helperText={errors.email}
					/>
					<TextField
						value={password}
						onChange={event => setPassword(event.target.value)}
						className={styles.form_body_input}
						label="Password"
						variant="outlined"
						type="password"
						required
						error={!!errors.password}
						helperText={errors.password}
					/>
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
