import React from "react";
import { Button } from "@mui/material";
import { NavLink } from "react-router-dom";

import styles from "./styles/Sidebar.module.css";

function Sidebar({ routes }) {
	const renderButton = (text, link, key) => (
		<NavLink className={({ isActive }) => (isActive ? styles.activeLink : styles.link)} to={link} key={key}>
			<Button variant="text" className={styles.button}>
				{text}
			</Button>
		</NavLink>
	);

	return (
		<>
			<div className={styles.logo}></div>
			<>
				{routes.map((r, i) => {
					const route = r.replace("/", "");
					return renderButton(route, route, i);
				})}
			</>
		</>
	);
}

export default Sidebar;
