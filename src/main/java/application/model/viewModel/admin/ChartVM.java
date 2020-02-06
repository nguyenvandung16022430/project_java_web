package application.model.viewModel.admin;


import application.model.viewModel.common.ChartDataVM;
import application.model.viewModel.common.LayoutHeaderAdminVM;

import java.util.List;

public class ChartVM {

    private LayoutHeaderAdminVM layoutHeaderAdminVM;

    private List<ChartDataVM> chartDataVMS;

    public List<ChartDataVM> getChartDataVMS() {
        return chartDataVMS;
    }

    public void setChartDataVMS(List<ChartDataVM> chartDataVMS) {
        this.chartDataVMS = chartDataVMS;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

}
