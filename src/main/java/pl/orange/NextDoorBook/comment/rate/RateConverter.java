package pl.orange.NextDoorBook.comment.rate;

import jakarta.persistence.AttributeConverter;

public class RateConverter implements AttributeConverter<Rate,Integer> {

    @Override
    public Integer convertToDatabaseColumn(Rate rate) {
        return rate != null ? rate.getValue() : null;
    }

    @Override
    public Rate convertToEntityAttribute(Integer value) {
        if (value != null) {
            switch (value) {
                case 1:
                    return Rate.ONE;
                case 2:
                    return Rate.TWO;
                case 3:
                    return Rate.THREE;
                case 4:
                    return Rate.FOUR;
                case 5:
                    return Rate.FIVE;
            }
        }
        return null;
    }
}
