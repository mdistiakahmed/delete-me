import { render, screen } from '@testing-library/react';
import Modal from '../Modal';

const testTitle: JSX.Element = <div>Test Title</div>;
const testBody: JSX.Element = <div>Test Body</div>;
const testModalActions: JSX.Element = <div>Test Actions</div>;

test('Modal renders properly when isOpen flag is true', async () => {
    render(
        <Modal
            isOpen={true}
            title={testTitle}
            body={testBody}
            modalActions={testModalActions}
            onClose={() => console.log('On Close')}
        />,
    );
    expect(screen.queryByText('Test Title')).toBeTruthy();
    expect(screen.queryByText('Test Body')).toBeTruthy();
    expect(screen.queryByText('Test Actions')).toBeTruthy();
});

test('Modal get hidden when isOpen flag is false', () => {
    render(
        <Modal
            isOpen={false}
            title={testTitle}
            body={testBody}
            modalActions={testModalActions}
            onClose={() => console.log('On Close')}
        />,
    );
    expect(screen.queryByText('Test Title')).toBeFalsy();
    expect(screen.queryByText('Test Body')).toBeFalsy();
    expect(screen.queryByText('Test Actions')).toBeFalsy();
});
