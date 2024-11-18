SELECT S.ID, S.GENOTYPE, P.GENOTYPE AS PARENT_GENOTYPE
FROM ECOLI_DATA AS S JOIN ECOLI_DATA AS P
ON S.PARENT_ID = P.ID
WHERE (S.GENOTYPE & P.GENOTYPE) = P.GENOTYPE
ORDER BY S.ID
