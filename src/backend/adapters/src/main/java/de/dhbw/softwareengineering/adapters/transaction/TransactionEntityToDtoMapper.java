package de.dhbw.softwareengineering.adapters.transaction;

import de.dhbw.softwareengineering.adapters.values.DescriptionDto;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionEntityToDtoMapper {

    public TransactionDto mapEntityToDto(TransactionEntity entity){
        TransactionDto dto = new TransactionDto();
        dto.setTransactionId(entity.getTransactionId());
        dto.setAmount(entity.getAmount());

        DescriptionDto description = new DescriptionDto();
        description.setText(entity.getDescription().getText());
        dto.setDescription(description);

        dto.setType(entity.getType());
        dto.setUnit(entity.getUnit());
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }

}
