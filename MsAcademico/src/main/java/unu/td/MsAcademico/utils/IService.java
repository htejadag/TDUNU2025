package unu.td.MsAcademico.utils;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IService<TResponse, TRequest> {

    public List<TResponse> getAll();

    public TResponse getById(Integer id);

    @Transactional
    public TResponse add(TRequest request);

    @Transactional
    public TResponse update(Integer id, TRequest request);

    @Transactional
    public void delete(Integer id);

    @Transactional
    public void activate(Integer id);

    @Transactional
    public void deactivate(Integer id);
}
