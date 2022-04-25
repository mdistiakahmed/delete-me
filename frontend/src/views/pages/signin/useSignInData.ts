import { useEffect, useState } from 'react';
import useAuthService from '../../../services/useAuthService';
import * as yup from 'yup';
import { AnyObjectSchema } from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from 'react-hook-form';
import { UserDataRequest } from '../../../constants/DataType';
import { UiTextMessages } from '../../../constants/UiTextMessages';

const defaultValues = {
    email: '',
    password: '',
};

const VALIDATION_SCHEMA: AnyObjectSchema = yup.object({
    email: yup.string().required(UiTextMessages.signInPageMessages.emailError),
    password: yup
        .string()
        .required(UiTextMessages.signInPageMessages.passwordError),
});

const useSignInData = () => {
    const { handleSubmit, control } = useForm<UserDataRequest>({
        defaultValues: defaultValues,
        resolver: yupResolver(VALIDATION_SCHEMA),
    });

    const [busy, setBusy] = useState<boolean>(false);
    const { signIn } = useAuthService();

    useEffect(() => {
        // clear memory during exist form this page (component unmount)
        return () => {
            setBusy(false);
        };
    }, []);

    const handleSignInFormSubmit = async (data: UserDataRequest) => {
        setBusy(true);
        signIn(data).finally(() => {
            setBusy(false);
        });
    };

    return {
        handleSubmit,
        control,
        handleSignInFormSubmit,
        busy,
    };
};

export default useSignInData;
