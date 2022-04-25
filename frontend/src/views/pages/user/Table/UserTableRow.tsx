import TableCell from '@mui/material/TableCell';
import TableRow from '@mui/material/TableRow';
import Grid from '@mui/material/Grid';
import EditIcon from '@mui/icons-material/Edit';
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import { useContext, useState } from 'react';
import IconButton from '@mui/material/IconButton';
import { UserDataContext } from '../../../../context/UserDataContext';
import UserDeleteModal from '../Modals/UserDeleteModal';
import { UserRoles } from '../../../../constants/GeneralConstants';

const UserTableRow = ({ email, role }: UserTableRowProps): JSX.Element => {
    const [deleteConfirmationOpen, setDeleteConfirmationOpen] =
        useState<boolean>(false);
    const { deleteData } = useContext(UserDataContext);

    const onDeleteConfirm = async () => {
        deleteData(email).then(() => setDeleteConfirmationOpen(false));
    };

    return (
        <TableRow
            key={email}
            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
        >
            <TableCell component="th" scope="row" align="center">
                {email}
            </TableCell>
            <TableCell align="center">{role}</TableCell>
            <TableCell align="center">
                <Grid
                    container
                    spacing={2}
                    alignItems="center"
                    justifyContent="center"
                >
                    <Grid item>
                        <IconButton onClick={() => ({})} aria-label="edit">
                            <EditIcon />
                        </IconButton>
                    </Grid>
                    <Grid item>
                        <IconButton
                            onClick={() => setDeleteConfirmationOpen(true)}
                            aria-label="delete"
                        >
                            <DeleteForeverIcon sx={{ fill: 'red' }} />
                        </IconButton>
                    </Grid>
                </Grid>
            </TableCell>

            <UserDeleteModal
                isOpen={deleteConfirmationOpen}
                onCancel={() => setDeleteConfirmationOpen(false)}
                onConfirm={onDeleteConfirm}
                email={email}
                role={role}
            />
        </TableRow>
    );
};

export default UserTableRow;

export type UserTableRowProps = {
    email: string;
    role: UserRoles;
};
