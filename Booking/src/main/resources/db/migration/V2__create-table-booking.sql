CREATE TABLE bookings(
    id UUID PRIMARY KEY,
    room_id UUID NOT NULL,
    room_number NUMERIC(10) NOT NULL,
    name_guest VARCHAR(150) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    telephone VARCHAR(11) NOT NULL,
    message TEXT,
    status booking_status NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    guest_cpf VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)