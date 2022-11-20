import React from "react";
import { SiTrainerroad } from "react-icons/si";
import { GrLogout } from "react-icons/gr";
import { AppBar, Box, Toolbar, Button } from "@mui/material";
import { NavLink } from "react-router-dom";

import styles from "./styles/Header.module.css";

function Header({ routes }) {
	const renderButton = (text, link, key) => (
		<NavLink className={({ isActive }) => (isActive ? styles.activeLink : styles.link)} to={link} key={key}>
			<Button variant="text" className={styles.button}>
				{text}
			</Button>
		</NavLink>
	);

	return (
		<Box>
			<AppBar style={{ background: "transparent" }} position="static">
				<Toolbar className={styles.toolbar}>
					<div>
						<SiTrainerroad color="#0026ff" size={36} />
					</div>
					<div>
						{routes.map((r, i) => {
							const route = r.replace("/", "");
							return renderButton(route, route, i);
						})}
					</div>
					<div>{renderButton(<GrLogout color="#0026ff" size={28} />, "/logout", "logout")}</div>
				</Toolbar>
			</AppBar>
		</Box>
	);
}

export default Header;
