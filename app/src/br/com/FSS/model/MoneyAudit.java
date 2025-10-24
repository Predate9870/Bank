package br.com.FSS.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MoneyAudit (
    UUID IDTransação,
    Bankservice targetServie,
    String description,
    OffsetDateTime createdAT)
{
    
}
