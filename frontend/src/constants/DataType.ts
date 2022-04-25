export type UserDataRequest = {
    email: string;
    password: string;
};

export type UserDataResponse = {
    email: string;
    id: number;
};

export type UserListPageResponse = {
    userList: UserDataResponse[];
    pageNumber: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
};

export type HttpSuccessResponse = {
    timestamp: string;
    status: number;
    body: any;
};

export type HttpErrorResponse = {
    status: number;
    message: any;
    error: string;
    timestamp: string;
    path: string;
};
