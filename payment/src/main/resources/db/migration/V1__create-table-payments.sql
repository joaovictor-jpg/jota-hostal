CREATE TABLE IF NOT EXISTS payments (
    id SERIAL PRIMARY KEY,
    guest_name VARCHAR(100) NOT NULL,
    guest_email VARCHAR(100) NOT NULL,
    card_number VARCHAR(20),
    card_validity DATE,
    payment_value NUMERIC(10,2) NOT NULL,
    payment_type VARCHAR(50),
    guest_cpf VARCHAR(14) NOT NULL,
    card_cvv VARCHAR(4) NOT NULL,
    country VARCHAR(50),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE NULL,

    CONSTRAINT valid_email CHECK (guest_email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    CONSTRAINT valid_cpf CHECK (guest_cpf ~* '^[0-9]{3}\.?[0-9]{3}\.?[0-9]{3}-?[0-9]{2}$')
);

COMMENT ON TABLE payments IS 'Tabela de registros de pagamentos do sistema';
COMMENT ON COLUMN payments.card_number IS 'Número do cartão de crédito (armazenado sem espaços ou traços)';
COMMENT ON COLUMN payments.card_cvv IS 'Código de segurança do cartão (3 ou 4 dígitos)';

CREATE INDEX IF NOT EXISTS idx_payments_cpf ON payments(guest_cpf);
CREATE INDEX IF NOT EXISTS idx_payments_email ON payments(guest_email);
CREATE INDEX IF NOT EXISTS idx_payments_created_at ON payments(created_at);

CREATE OR REPLACE FUNCTION update_payment_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_payment_timestamp
BEFORE UPDATE ON payments
FOR EACH ROW
EXECUTE FUNCTION update_payment_timestamp();