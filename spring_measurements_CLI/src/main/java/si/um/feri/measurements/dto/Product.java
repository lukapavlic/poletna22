package si.um.feri.measurements.dto;

public record Product (
        int id,
        String name,
        double maxMeasure,
        double minMeasure) {}