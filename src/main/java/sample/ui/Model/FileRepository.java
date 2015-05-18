package sample.ui.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;


@Repository
@Table(name = "Files")
public interface FileRepository extends JpaRepository<File, Long> {
}
