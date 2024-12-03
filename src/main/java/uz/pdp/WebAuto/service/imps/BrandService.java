package uz.pdp.WebAuto.service.imps;

public interface BrandService<E> {

    E getById(Long id);
    E update(E e);

}
