package subway.path.application;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.springframework.stereotype.Service;
import subway.line.application.LineService;
import subway.line.domain.Line;
import subway.line.domain.Section;
import subway.line.domain.Sections;
import subway.path.domain.Path;
import subway.path.dto.PathResponse;
import subway.station.application.StationService;
import subway.station.domain.Station;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PathService {

    private LineService lineService;
    private StationService stationService;

    public PathService(LineService lineService, StationService stationService) {
        this.lineService = lineService;
        this.stationService = stationService;
    }

    public PathResponse getShortestPath(Long sourceId, Long targetId) {
        List<Line> lines = lineService.findLines();
        Station sourceStation = stationService.findStationById(sourceId);
        Station targetStation = stationService.findStationById(targetId);

        Path path = Path.make(lines, sourceStation, targetStation);
        return PathResponse.make(path.getStations(), path.getDistance(), path.getFare());
    }

}
