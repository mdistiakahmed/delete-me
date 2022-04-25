import { FC } from 'react';
import UserTable from './Table/UserTable';
import useUserData from './useUserData';
import { UserDataContext } from '../../../context/UserDataContext';
import AddNewItemButton from '../../common-components/AddNewItemButton';
import UserCreateModal from './Modals/UserCreateModal';
import Topbar from '../../common-components/Topbar';

const User: FC = () => {
    const userLogic = useUserData();

    return (
        <UserDataContext.Provider value={userLogic}>
            <div>
                <Topbar />
                <UserTable />
                <AddNewItemButton
                    title="Add New User"
                    onClick={() => userLogic.setCreateModalOpen(true)}
                />
                <UserCreateModal
                    isOpen={userLogic.createModalOpen}
                    onCancel={() => userLogic.setCreateModalOpen(false)}
                />
            </div>
        </UserDataContext.Provider>
    );
};

export default User;
