export class ApiEndpoints {
    static auth = {
        signIn: `/v1/login`,
        signUp: `/v1/signup`,
    };

    static user = {
        getUsers: '/v1/users',
        createUser: '/v1/users',
        deleteUser: (username: string) => `/v1/users/${username}`,
        updateUser: '/v1/users',
    };
}

export const URLsWithoutAuthorization = [
    ApiEndpoints.auth.signIn,
    ApiEndpoints.auth.signUp,
];
