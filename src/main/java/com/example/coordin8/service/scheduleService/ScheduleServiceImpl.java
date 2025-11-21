package com.example.coordin8.service.scheduleService;

import com.example.coordin8.dto.SlotCountDto;
import com.example.coordin8.entity.*;
import com.example.coordin8.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository; // 필요하다고 가정
    private final ScheduleDayRepository scheduleDayRepository;
    private final ScheduleSlotRepository scheduleSlotRepository; // 필요하다고 가정
    private final AvailableScheduleRepository availableRepo;

    public ScheduleServiceImpl(UserRepository userRepository,
                               ProjectRepository projectRepository,
                               ScheduleDayRepository scheduleDayRepository,
                               ScheduleSlotRepository scheduleSlotRepository,
                               AvailableScheduleRepository availableRepo) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.scheduleDayRepository = scheduleDayRepository;
        this.scheduleSlotRepository = scheduleSlotRepository;
        this.availableRepo = availableRepo;
    }

    @Override
    @Transactional
    public void createScheduleDays(ProjectEntity project) {
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            ScheduleDayEntity day = new ScheduleDayEntity();
            day.setProject(project);
            day.setDate(today.plusDays(i));
            scheduleDayRepository.save(day);
        }
    }

    @Override
    @Transactional
    public void saveAvailableSchedule(Long userId, Long projectId, Long dayId, Long slotId) {

        // orElseThrow()는 NoSuchElementException을 던집니다.
        UserEntity user = userRepository.findById(userId).orElseThrow();
        ProjectEntity project = projectRepository.findById(projectId).orElseThrow();
        ScheduleDayEntity day = scheduleDayRepository.findById(dayId).orElseThrow();
        ScheduleSlotEntity slot = scheduleSlotRepository.findById(slotId).orElseThrow();

        if (!availableRepo.existsByUserAndProjectAndScheduleDayAndScheduleSlot(user, project, day, slot)) {

            AvailableScheduleEntity save = new AvailableScheduleEntity();
            save.setUser(user);
            save.setProject(project);
            save.setScheduleDay(day);
            save.setScheduleSlot(slot);
            save.setAvailable(true); // 명시

            availableRepo.save(save);
        }
    }

    // 자동 생성 확인 및 조회 구현
    @Override
    public List<ScheduleDayEntity> getScheduleDaysByProject(Long projectId) {
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));

        return scheduleDayRepository.findByProject(project);
    }

    // 가용성 집계 조회 구현
    @Override
    public List<SlotCountDto> getAvailabilityCountsByProject(Long projectId) {
        // 프로젝트 존재 확인은 Optional 처리에서 이미 수행될 수 있지만, 명확성을 위해 추가합니다.
        if (!projectRepository.existsById(projectId)) {
            throw new EntityNotFoundException("Project not found with ID: " + projectId);
        }
        return availableRepo.findAvailabilityCounts(projectId);
    }
}