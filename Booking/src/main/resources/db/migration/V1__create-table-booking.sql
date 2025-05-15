CREATE TABLE bookings(
    id UUID PRIMARY KEY,
    room_number NUMERIC(10) NOT NULL,
    email VARCHAR(150) NOT NULL,
    name_guest VARCHAR(150) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    telephone VARCHAR(17) NOT NULL,
    message TEXT,
    status VARCHAR(50) NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    guest_cpf VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)