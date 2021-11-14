const BASE_URL = 'http://localhost:8080';

// Auth
export const LOGIN_URL = `${BASE_URL}/auth/login`;

// Users
export const USERS_URL = `${BASE_URL}/api/users`;
export const REGISTER_URL = `${USERS_URL}/public/register`;
export const VERIFY_ACC_URL = `${USERS_URL}/public/verify-account`;
