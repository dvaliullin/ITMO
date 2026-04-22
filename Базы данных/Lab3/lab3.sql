CREATE OR REPLACE FUNCTION calculate_area()
RETURNS TRIGGER AS $$
DECLARE
    PI CONSTANT NUMERIC := 3.14;
BEGIN
    IF NEW.coverage IS NOT NULL AND NEW.perimeter IS NOT NULL AND
(NEW.area IS NULL OR OLD.perimeter IS DISTINCT FROM NEW.perimeter OR NEW.coverage IS DISTINCT FROM OLD.coverage) THEN
        CASE NEW.coverage
            WHEN 'SQUARE' THEN
                NEW.area := NEW.perimeter ^ 2 / 16;
            WHEN 'CIRCLE' THEN
                NEW.area := NEW.perimeter ^ 2 / (PI * 3);
            ELSE
                NEW.area := NULL;
        END CASE;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER calculate_area_trigger
BEFORE INSERT OR UPDATE ON excavation_site
FOR EACH ROW
EXECUTE FUNCTION calculate_area();
