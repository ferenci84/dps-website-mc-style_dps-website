package dps.webapplication.startup;

import dps.commons.startup.Startup;
import dps.markdown.Markdown;
import org.commonmark.ext.gfm.tables.TablesExtension;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Startup
public class InstallMdTableExtension {

    @Inject
    Markdown md;

    @PostConstruct
    void init() {
        md.addExtension(TablesExtension.create());
        md.rebuild();
    }

}
