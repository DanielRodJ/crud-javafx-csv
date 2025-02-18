package com.github.danielrodj.services;

import com.github.danielrodj.database.BasicRepository;
import com.github.danielrodj.interfaces.LabelService;
import com.github.danielrodj.models.label.Label;
import com.github.danielrodj.models.label.PrintedLabel;
import com.github.danielrodj.models.label.ReprintedLabel;
import com.github.danielrodj.repositories.PLabelRepositoryCSV;
import com.github.danielrodj.repositories.RLabelRepositoryCSV;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractLabelService<T extends Label<?>> implements LabelService<T> {

    protected final BasicRepository<T> labelRepositoryCSV;

    public AbstractLabelService(BasicRepository<T> labelRepositoryCSV) {
        this.labelRepositoryCSV = labelRepositoryCSV;
    }

    @Override
    public T get(int id) {
        return labelRepositoryCSV.get(id);
    }

    @Override
    public Map<Integer, T> getAll() {
        return labelRepositoryCSV.getAll();
    }

    @Override
    public int insert(T label) {
        return labelRepositoryCSV.insert(label);
    }

    @Override
    public int update(T label) {
        return labelRepositoryCSV.update(label);
    }

    @Override
    public int delete(T Label) {
        return labelRepositoryCSV.delete(Label);
    }

    @Override
    public Integer getRequesterIdByLabelId(Integer id) {
        return get(id).getRequesterId();
    }

    @Override
    public Integer getPrinterIdByLabelId(Integer id) {
        return get(id).getPrinterId();
    }

    @Override
    public Integer getCategoryIdByLabelId(Integer id) {
        return get(id).getCategoryId();
    }

    @Override
    public Integer getOriginalEditorIdByLabelId(Integer id) {
        return get(id).getOriginalEditorId();
    }

    @Override
    public Integer getLastEditorIdByLabelId(Integer id) {
        return get(id).getLastEditorId();
    }

    @Override
    public String getReasonsByLabelId(Integer id) {
        return get(id).getReasons();
    }

    @Override
    public String getRequestDateByLabelId(Integer id) {
        return get(id).getRequestDate();
    }

    @Override
    public String getResponseDateByLabelId(Integer id) {
        return get(id).getResponseDate();
    }

    @Override
    public LocalDate getParseRequestDateByLabelId(Integer id) throws ParseException {
        return LocalDate.parse(get(id).getRequestDate());
    }

    @Override
    public LocalDate getParseResponseDateByLabelId(Integer id) throws ParseException {
        return LocalDate.parse(get(id).getResponseDate());
    }

    @Override
    public Integer deleteLabelByLabelId(Integer id) {
        return labelRepositoryCSV.delete(get(id));
    }

    @Override
    public List<Label<?>> getAllLabels() {
        List<Label<?>> listLabels = new ArrayList<>();
        Map<Integer, PrintedLabel> printedLabels = new PLabelRepositoryCSV().getAll();
        Map<Integer, ReprintedLabel> reprintedLabels = new RLabelRepositoryCSV().getAll();
        listLabels.addAll(printedLabels.values());
        listLabels.addAll(reprintedLabels.values());
        return listLabels;
    }

    public List<Label<?>> getFilteredLabels(Integer categoryId, Integer requesterId,
        Integer printedId, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = parseDate(startDate, formatter);
        LocalDate end = parseDate(endDate, formatter);

        return getAllLabels().stream()
                .filter(label -> (categoryId == null || label.getCategoryId() == categoryId))
                .filter(label -> (requesterId == null || label.getRequesterId() == requesterId))
                .filter(label -> (printedId == null || label.getPrinterId() == printedId))
                .filter(label -> {
                    if (start == null && end == null) {
                        return true;
                    } else if (start != null && end == null) {
                        return LocalDate.parse(label.getRequestDate(), formatter).isAfter(start)
                                || LocalDate.parse(label.getRequestDate(), formatter).isEqual(start);
                    } else if (start == null && end != null) {
                        return LocalDate.parse(label.getRequestDate(), formatter).isBefore(end)
                                || LocalDate.parse(label.getRequestDate(), formatter).isEqual(end);
                    } else {
                        LocalDate requestDate = LocalDate.parse(label.getRequestDate(), formatter);
                        return (requestDate.isAfter(start) || requestDate.isEqual(start))
                                && (requestDate.isBefore(end) || requestDate.isEqual(end));
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Label<?>> getAllLabelsByRequester(Integer categoryId, Integer requesterId,
            Integer printedId, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = parseDate(startDate, formatter);
        LocalDate end = parseDate(endDate, formatter);

        return getAllLabels().stream()
                .filter(label -> (categoryId == null || label.getCategoryId() == categoryId))
                .filter(label -> (requesterId == null || label.getRequesterId() == requesterId))
                .filter(label -> (printedId == null || label.getPrinterId() == printedId))
                .filter(label -> {
                    if (start == null && end == null) {
                        return true;
                    } else if (start != null && end == null) {
                        return LocalDate.parse(label.getRequestDate(), formatter).isAfter(start)
                                || LocalDate.parse(label.getRequestDate(), formatter).isEqual(start);
                    } else if (start == null && end != null) {
                        return LocalDate.parse(label.getRequestDate(), formatter).isBefore(end)
                                || LocalDate.parse(label.getRequestDate(), formatter).isEqual(end);
                    } else {
                        LocalDate requestDate = LocalDate.parse(label.getRequestDate(), formatter);
                        return (requestDate.isAfter(start) || requestDate.isEqual(start))
                                && (requestDate.isBefore(end) || requestDate.isEqual(end));
                    }
                })
                .collect(Collectors.toList());
    }

    private LocalDate parseDate(String date, DateTimeFormatter formatter) {
        return date != null && !date.isEmpty() ? LocalDate.parse(date, formatter) : null;
    }

}