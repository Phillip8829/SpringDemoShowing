package com.galvanize.cerialCats.Repo;

import com.galvanize.cerialCats.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Report,Long> {
}
