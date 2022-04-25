import { useEffect, useState } from 'react';
import {
    UserDataRequest,
    UserListPageResponse,
} from '../../../constants/DataType';
import useUserService from '../../../services/useUserService';
//import { PageLimit } from '../../../constants/GeneralConstants';

const useUserData = () => {
    const [userTableData, setUserTableData] = useState<UserListPageResponse>({
        userList: [],
        pageNumber: 0,
        pageSize: 0,
        totalElements: 0,
        totalPages: 0,
    });
    const [createModalOpen, setCreateModalOpen] = useState<boolean>(false);
    const userService = useUserService();

    const setPageNumber = (pageNo: number) => {
        setUserTableData({
            ...userTableData,
            pageNumber: pageNo,
        });
    };

    const loadData = async (): Promise<any> => {
        // set loader state = true
        userService
            .getAllUsers(userTableData.pageNumber, 5) //PageLimit.USER_PAGE_LIMIT
            .then((res: UserListPageResponse) => {
                setUserTableData(res);
                return Promise.resolve(res);
            });
    };

    const deleteData = async (username: string): Promise<any> => {
        return userService.deleteUser(username).then(async (result) => {
            return loadData();
        });
    };

    const createData = async (data: UserDataRequest): Promise<any> => {
        return userService
            .createUser(data)
            .then(async (result) => {
                loadData();
                return Promise.resolve();
            })
            .catch((err) => {
                return Promise.reject(err);
            });
    };

    useEffect(() => {
        loadData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [userTableData.pageNumber]);

    return {
        loadData,
        deleteData,
        createData,
        setPageNumber,
        userTableData,
        createModalOpen,
        setCreateModalOpen,
    };
};

export default useUserData;
