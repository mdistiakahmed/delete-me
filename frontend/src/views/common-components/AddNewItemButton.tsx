import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';

const AddNewItemButton = ({
    onClick,
    title,
}: AddNewItemButtonProps): JSX.Element => {
    return (
        <Tooltip title={title}>
            <IconButton
                aria-label="add"
                sx={{ position: 'fixed', zIndex: 999, bottom: 10, right: 5 }}
                onClick={onClick}
            >
                <AddCircleOutlineIcon
                    color="primary"
                    sx={{
                        ':hover': { transform: 'scale(1.2)' },
                        minHeight: 50,
                        minWidth: 50,
                    }}
                />
            </IconButton>
        </Tooltip>
    );
};

export default AddNewItemButton;

type AddNewItemButtonProps = {
    onClick: () => void;
    title: string;
};
