package uz.pdp.WebAuto.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "company_statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CompanyStatistic extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "total_cars")
    private Long totalCars;

    @Column(name = "total_brands")
    private Long totalBrands;

    @Column(name = "total_orders")
    private Long totalOrders;

    @Column(name = "total_users")
    private Long totalUsers;

    @Column(name = "total_comments")
    private Long totalComments;

}
