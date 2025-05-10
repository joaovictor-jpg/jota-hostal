CREATE TYPE booking_status AS ENUM (
    'PENDING',
    'CONFIRMED',
    'CANCELLED',
    'REJECTED',
    'EXPIRED',
    'CHECKED_IN',
    'CHECKED_OUT',
    'NO_SHOW'
);
