import { Button, TextField } from "@mui/material";
import { useState } from "react";

import { sendMessage } from "../../services/notify";

import styles from "./styles/Notify.module.css";

function Notify() {
	const [recipient, setRecipient] = useState("");
	const [subject, setSubject] = useState("");
	const [message, setMessage] = useState("");

	const onSend = () => {
		const data = { to: recipient, subject, message };
		sendMessage(data).then(() => {
			setRecipient("");
			setSubject("");
			setMessage("");
		});
	};

	const renderTextField = (value, onChange, label, type, isRequired, isMultiline) => {
		return (
			<TextField
				value={value}
				onChange={event => onChange(event.target.value)}
				className={styles.form_body_input}
				label={label}
				type={type}
				variant="outlined"
				required={isRequired}
				multiline={isMultiline}
				rows={isMultiline ? 4 : 1}
			/>
		);
	};

	return (
		<div className={styles.wrapper}>
			<div className={styles.form}>
				<div className={styles.form_header}>
					<h4 className={styles.form_header_text}>Notify about changes in the schedule</h4>
				</div>
				<div className={styles.form_body}>
					{renderTextField(recipient, setRecipient, "Recipient", "email", true, false)}
					{renderTextField(subject, setSubject, "Subject", "text", false, false)}
					{renderTextField(message, setMessage, "Message", "text", true, true)}
					<Button
						onClick={e => {
							e.preventDefault();
							onSend();
						}}
						className={styles.form_body_button}
						variant="contained">
						Send message
					</Button>
				</div>
			</div>
		</div>
	);
}

export default Notify;
