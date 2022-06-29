import { useState } from 'react';

import Nav from '../components/Nav';
import styles from '../styles/Home.module.css';

export default function AddPost() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [error, setError] = useState('');
    const [message, setMessage] = useState('');

    const handlePost = async (e) => {
        e.preventDefault();

        setError('');
        setMessage('');

        if (!title || !content) return setError('All fields are required');

        let post = {
            title,
            content,
            published: false,
            createdAt: new Date().toISOString(),
        };

        let response = await fetch('/api/posts', {
            method: 'POST',
            body: JSON.stringify(post),
        });

        let data = await response.json();

        if (data.success) {
            setTitle('');
            setContent('');
            return setMessage(data.message);
        } else {
            return setError(data.message);
        }
    };

    return (
        <div>
            <Nav />
            <div className={styles.container}>
                <form onSubmit={handlePost} className={styles.form}>
                    {error ? (
                        <div className={styles.formItem}>
                            <h3 className={styles.error}>{error}</h3>
                        </div>
                    ) : null}
                    {message ? (
                        <div className={styles.formItem}>
                            <h3 className={styles.message}>{message}</h3>
                        </div>
                    ) : null}
                    <div className={styles.formItem}>
                        <label>Naslov</label>
                        <input
                            type="text"
                            name="title"
                            onChange={(e) => setTitle(e.target.value)}
                            value={title}
                            placeholder="naslov"
                        />
                    </div>
                    <div className={styles.formItem}>
                        <label>Vsebina</label>
                        <textarea
                            name="vsebina"
                            onChange={(e) => setContent(e.target.value)}
                            value={content}
                            placeholder="Dodaj vsebino"
                        />
                    </div>
                    <div className={styles.formItem}>
                        <button type="submit">Dodaj objavo</button>
                    </div>
                </form>
            </div>
        </div>
    );
}