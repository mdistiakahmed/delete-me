import { FC } from 'react';
import { Box, Typography } from '@mui/material';

const NoDataMessage: FC = () => {
    return (
        <Box>
            <Box
                component="img"
                alt="No Data"
                src="./empty_box.jpg"
                sx={{
                    mx: 'auto',
                    mt: '70px',
                    display: 'block',
                    width: 'min(40%,300px)',
                    opacity: '0.3',
                }}
            ></Box>
            <Typography variant="h5" fontFamily={'sans-serif'} color="#898989">
                nothing to see here
            </Typography>
        </Box>
    );
};

export default NoDataMessage;
