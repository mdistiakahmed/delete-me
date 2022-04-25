import { sum } from './sum';

it('summing test.....', () => {
    const a = 5;
    expect(a).toBe(5);
    expect(sum(1, 2)).toBe(15);
});
