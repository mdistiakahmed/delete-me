import { createContext } from 'react';
import { UserDataRequest, UserListPageResponse } from '../constants/DataType';

export const UserDataContext = createContext<UserDataContextType>({
    userTableData: {
        userList: [],
        pageNumber: 0,
        pageSize: 0,
        totalElements: 0,
        totalPages: 0,
    },
    loadData: () => {
        return Promise.resolve();
    },
    deleteData: () => {
        return Promise.resolve();
    },
    createData: () => {
        return Promise.resolve();
    },
    setPageNumber: () => ({}),
    createModalOpen: false,
    setCreateModalOpen: () => ({}),
});

export type UserDataContextType = {
    userTableData: UserListPageResponse;
    loadData: () => Promise<any>;
    deleteData: (username: string) => Promise<any>;
    createData: (data: UserDataRequest) => Promise<void>;
    setPageNumber: (pageNo: number) => void;
    createModalOpen: boolean;
    setCreateModalOpen: any;
};
