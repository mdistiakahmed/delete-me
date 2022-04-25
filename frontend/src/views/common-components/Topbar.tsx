import { AppBar, Box, ButtonGroup, Toolbar, Typography } from '@mui/material';
import Button from '@mui/material/Button';
import { FC, useContext } from 'react';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import { useLocation, useNavigate } from 'react-router-dom';
import { AppReducerActionKind } from '../../hooks/useAppReducer';
import { ApplicationContext } from '../../context/AppContext';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const { palette } = createTheme();
const theme = createTheme({
    palette: {
        primary: {
            main: '#e3f2fd',
        },
        secondary: {
            main: '#4db6ac',
        },
    },
});

const Topbar: FC = () => {
    const { dispatch } = useContext(ApplicationContext);
    const navigate = useNavigate();
    const { pathname } = useLocation();

    const handleLogout = async () => {
        dispatch({ type: AppReducerActionKind.REMOVE_TOKEN, payload: {} });
    };

    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6"> My Awsome App </Typography>
                <ThemeProvider theme={theme}>
                    <ButtonGroup
                        size="large"
                        variant="text"
                        aria-label="text button group"
                        sx={{ marginLeft: '10px', flexGrow: 1 }}
                    >
                        <Button
                            sx={getButtonStyle(pathname === '/')}
                            onClick={() => navigate('/')}
                        >
                            Home
                        </Button>
                        <Button
                            sx={getButtonStyle(pathname === '/users')}
                            onClick={() => navigate('/users')}
                        >
                            Users
                        </Button>
                    </ButtonGroup>
                    <Box sx={{ flexGrow: 0 }}>
                        <Button
                            startIcon={<ExitToAppIcon />}
                            onClick={handleLogout}
                        >
                            Logout
                        </Button>
                    </Box>
                </ThemeProvider>
            </Toolbar>
        </AppBar>
    );
};

export default Topbar;

const getButtonStyle = (isSelected: boolean) => {
    return isSelected ? selectedButtonStyle : unselectedButtonStyle;
};

const unselectedButtonStyle = {
    '&.MuiButton-text': {
        color: 'white',
        fontWeight: 'bold',
        fontSize: '20px',
    },
    '&.MuiButton-text:hover': { color: 'black' },
};

const selectedButtonStyle = {
    '&.MuiButton-text': { color: '#acafcb' },
};
