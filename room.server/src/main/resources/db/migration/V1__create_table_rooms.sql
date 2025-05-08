CREATE TABLE rooms (
    id_room UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    area_m2 NUMERIC(5,2),
    room_number NUMERIC(10) NOT NULL,
    rental_value DECIMAL(10,2) NOT NULL,
    condo_fee DECIMAL(10,2),
    accepts_animals BOOLEAN DEFAULT false NOT NULL,
    address TEXT,
    description TEXT,
    telephone VARCHAR(20) NOT NULL,
    room_status VARCHAR(20) NOT NULL,
    image_url VARCHAR(255),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ
)