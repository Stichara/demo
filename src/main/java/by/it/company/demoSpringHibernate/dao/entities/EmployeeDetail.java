package by.it.company.demoSpringHibernate.dao.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "employee_detail")
public class EmployeeDetail implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "employee_uid", nullable = false)
    private Long employeeUId;

    @Column(name = "address")
    private String address;

    @OneToOne
    private Employee employee;

    public EmployeeDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeUId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeUId = employeeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeDetail that = (EmployeeDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (employeeUId != null ? !employeeUId.equals(that.employeeUId) : that.employeeUId != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (employeeUId != null ? employeeUId.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
