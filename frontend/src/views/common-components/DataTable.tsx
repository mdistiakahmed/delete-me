import { FC } from 'react';
import {
    Paper,
    styled,
    Table,
    TableCell,
    tableCellClasses,
    TableContainer,
    TableHead,
    TablePagination,
} from '@mui/material';

const DataTable: FC<DataTableProps> = ({
    title,
    headerRow,
    dataRow,
    totalElements,
    pageSize,
    pageNumber,
    onChangePage,
}) => {
    return (
        <Paper sx={{ mx: '70px', mt: '5px' }}>
            <TableContainer>
                <Table aria-label={title}>
                    <TableHead>{headerRow}</TableHead>
                    {dataRow}
                </Table>
            </TableContainer>
            <TablePagination
                component="div"
                rowsPerPageOptions={[]}
                count={totalElements}
                rowsPerPage={pageSize}
                page={pageNumber}
                onPageChange={onChangePage}
            />
        </Paper>
    );
};

export default DataTable;

type DataTableProps = {
    title: string;
    headerRow: JSX.Element;
    dataRow: JSX.Element;
    totalElements: number;
    pageSize: number;
    pageNumber: number;
    onChangePage: (event: any, page: number) => any;
};

export const StyledTableCell = styled(TableCell)(() => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: 'grey',
        color: 'white',
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));
