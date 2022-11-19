import { FcCancel } from "react-icons/fc";

import styles from "./styles/Empty.module.css";

function Empty() {
	return (
		<div className={styles.wrapper}>
			<FcCancel size={156} />
			<p className={styles.text}>
				No data found for this page!
				<br /> Select a page on the left to view the data.
			</p>
		</div>
	);
}

export default Empty;
