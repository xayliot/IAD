package ru.project.congratSystem.repository;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.congratSystem.model.Congratulation;
import ru.project.congratSystem.model.User;

import java.util.List;
import java.util.Optional;

@Repository
@NonNullApi
public interface CongratsRepo extends JpaRepository<Congratulation, Long> {

    Optional<Congratulation> findById(Long id);

    List<Congratulation> findByOwnerOfCongrat(User ownerOfCongrat);
}
