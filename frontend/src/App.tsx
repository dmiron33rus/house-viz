import { useState } from 'react';
import { login, register } from './api/auth';

export default function App() {
    const [tab, setTab] = useState<'login' | 'register'>('login');

    // Логин
    const [email, setEmail] = useState('manager@build.ru');
    const [password, setPassword] = useState('secret12345');

    // Регистрация
    const [regEmail, setRegEmail] = useState('manager@build.ru');
    const [regPassword, setRegPassword] = useState('secret12345');
    const [regFullName, setRegFullName] = useState('Иван Петров');
    const [regPhone, setRegPhone] = useState('+79001234567');
    const [regCompany, setRegCompany] = useState('СтройДом');

    const [user, setUser] = useState<any>(null);
    const [error, setError] = useState<string>('');
    const [loading, setLoading] = useState(false);

    const resetMessages = () => { setError(''); setUser(null); };

    const handleLogin = async () => {
        setLoading(true); resetMessages();
        try {
            const resp = await login({ email, password });
            localStorage.setItem('accessToken', resp.accessToken);
            setUser(resp.user);
        } catch (e: any) {
            setError(e.response?.data?.message ?? e.message ?? 'Ошибка');
        } finally {
            setLoading(false);
        }
    };

    const handleRegister = async () => {
        setLoading(true); resetMessages();
        try {
            const resp = await register({
                email: regEmail,
                password: regPassword,
                fullName: regFullName,
                phone: regPhone,
                companyName: regCompany,
            });
            localStorage.setItem('accessToken', resp.accessToken);
            setUser(resp.user);
        } catch (e: any) {
            setError(e.response?.data?.message ?? e.message ?? 'Ошибка');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={{ padding: 24, fontFamily: 'system-ui, sans-serif', maxWidth: 400 }}>
            <h1>HouseViz</h1>

            <div style={{ display: 'flex', gap: 8, marginBottom: 16 }}>
                <button
                    onClick={() => { setTab('login'); resetMessages(); }}
                    style={{ padding: '6px 12px', fontWeight: tab === 'login' ? 'bold' : 'normal' }}
                >
                    Вход
                </button>
                <button
                    onClick={() => { setTab('register'); resetMessages(); }}
                    style={{ padding: '6px 12px', fontWeight: tab === 'register' ? 'bold' : 'normal' }}
                >
                    Регистрация
                </button>
            </div>

            {tab === 'login' && (
                <div style={{ display: 'flex', flexDirection: 'column', gap: 8 }}>
                    <input
                        type="email" placeholder="Email"
                        value={email} onChange={(e) => setEmail(e.target.value)}
                        style={{ padding: 8, fontSize: 14 }}
                    />
                    <input
                        type="password" placeholder="Пароль"
                        value={password} onChange={(e) => setPassword(e.target.value)}
                        style={{ padding: 8, fontSize: 14 }}
                    />
                    <button onClick={handleLogin} disabled={loading}
                            style={{ padding: 8, fontSize: 14, cursor: loading ? 'wait' : 'pointer' }}>
                        {loading ? 'Входим...' : 'Войти'}
                    </button>
                </div>
            )}

            {tab === 'register' && (
                <div style={{ display: 'flex', flexDirection: 'column', gap: 8 }}>
                    <input
                        type="email" placeholder="Email"
                        value={regEmail} onChange={(e) => setRegEmail(e.target.value)}
                        style={{ padding: 8, fontSize: 14 }}
                    />
                    <input
                        type="password" placeholder="Пароль (мин. 8 символов)"
                        value={regPassword} onChange={(e) => setRegPassword(e.target.value)}
                        style={{ padding: 8, fontSize: 14 }}
                    />
                    <input
                        type="text" placeholder="ФИО"
                        value={regFullName} onChange={(e) => setRegFullName(e.target.value)}
                        style={{ padding: 8, fontSize: 14 }}
                    />
                    <input
                        type="text" placeholder="Телефон"
                        value={regPhone} onChange={(e) => setRegPhone(e.target.value)}
                        style={{ padding: 8, fontSize: 14 }}
                    />
                    <input
                        type="text" placeholder="Название компании"
                        value={regCompany} onChange={(e) => setRegCompany(e.target.value)}
                        style={{ padding: 8, fontSize: 14 }}
                    />
                    <button onClick={handleRegister} disabled={loading}
                            style={{ padding: 8, fontSize: 14, cursor: loading ? 'wait' : 'pointer' }}>
                        {loading ? 'Регистрируем...' : 'Зарегистрироваться'}
                    </button>
                </div>
            )}

            {error && <p style={{ color: 'red', marginTop: 12 }}>Ошибка: {error}</p>}

            {user && (
                <div style={{ marginTop: 16, padding: 12, background: '#e7f5e7', borderRadius: 4 }}>
                    <strong>Успешно!</strong>
                    <pre style={{ margin: '8px 0 0', fontSize: 12 }}>{JSON.stringify(user, null, 2)}</pre>
                </div>
            )}
        </div>
    );
}