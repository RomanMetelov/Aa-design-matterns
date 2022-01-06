package autocode.observer.git;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GitRepoObservers {

    public static Repository newRepository(){
        Repository repository = new Repository() {
            List<WebHook> listWebHooks = Collections.emptyList();

            @Override
            public void addWebHook(WebHook webHook) {
                this.listWebHooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Commit commit = new Commit(author, changes);
                Event.Type eventType = Event.Type.COMMIT;
                WebHook webHook = null;
                for (WebHook hook : listWebHooks){
                    if (hook.branch().equals(branch) && hook.type() == eventType) webHook = hook;
                }
                webHook.caughtEvents().add(new Event(eventType, branch, Arrays.asList(commit)));//try catch
                return commit;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                WebHook sourceBranchWebHook;
                WebHook targetBranchWebHook;
                Event.Type eventType = Event.Type.MERGE;

                for (WebHook hook : listWebHooks){
                    if (hook.branch().equals(sourceBranch) && hook.type() == eventType) sourceBranchWebHook = hook;
                    if (hook.branch().equals(targetBranch) && hook.type() == eventType) targetBranchWebHook = hook;
                }
                targetBranchWebHook.caughtEvents()

                //var = find commits with tag sourcebranch
                //var2 = find commits with tag targetbranch
                //stoolit' v ku4u var i var2
            }
        };

        return repository;
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        throw new UnsupportedOperationException();
    }

    public static WebHook commitToBranchWebHook(String branchName){
        WebHook webHook = new WebHook() {
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.COMMIT;
            }

            @Override
            public List<Event> caughtEvents() {

                return null;
            }

            @Override
            public void onEvent(Event event) {

            }
        };


        return webHook;

        throw new UnsupportedOperationException();
    }


}

//public class GitRepoObservers { List<Commit> commits = new ArrayList<>();
//
//    public static Repository newRepository() {
//        try {
//            Repository repository = new Repository() {
//
//                Map<String, Event> events = new HashMap();
//                Map<String, WebHook> webHooks = new HashMap();
//
//                @Override
//                public void addWebHook(WebHook webHook) { //1
//                    // уведомление о совершённых действиях в Интернет-системе
//                    // веб-перехватчик
//                    // содержит только название ветки
//
////                    commits.addAll(webHook.caughtEvents({this::commit }) );
////                    Event event = new Event(webHook.type(), webHook.branch(), webHook.caughtEvents().);
////                    events.addAll(webHook.caughtEvents());
//                    webHooks.put(webHook.branch() + webHook.type(), webHook);
//                    System.out.println("webhook " + webHook);
//                }
//
//                @Override
//                public Commit commit(String branch, String author, String[] changes) {//2
////Commit(final String author, final String[] changes)
//
//                    Commit commit = new Commit(author, changes);
//                    commits.add(commit);
//                    Event event = new Event(Event.Type.COMMIT, branch, commits);
//                    events.put(branch + Event.Type.COMMIT, event);
//                    //  events.add(event);
//
//                    WebHook webHook = webHooks.get(branch + Event.Type.COMMIT);
//                    System.out.println(webHook);
//                    webHook.onEvent(event);
//
////                    System.out.println(commits);
////                    System.out.println(events);
//                    return commit;
//                }
//
//                @Override
//                public void merge(String sourceBranch, String targetBranch) {//3
//                    System.out.println("source " + sourceBranch); // исходный
//                    System.out.println("target " + targetBranch); // целевой
//
//                    Event sourceEvent = events.get(sourceBranch + Event.Type.COMMIT);
//                    Event event = new Event(Event.Type.MERGE, targetBranch, sourceEvent.commits());
//                    events.put(targetBranch + Event.Type.MERGE, event);
//
//                  //  Event mergeEvent = new Event(Event.Type.MERGE, targetBranch, commits);
//
//                    WebHook webHook = webHooks.get(targetBranch + Event.Type.MERGE);
//                    System.out.println(webHook);
//                    webHook.onEvent(event);
//
//                }
//            };
//            return repository;
//        } catch (UnsupportedOperationException e) {
//            throw new UnsupportedOperationException();
//        }
//    }
//
//    public static WebHook mergeToBranchWebHook(String branchName) {
//        try {
//            WebHook webHook = new WebHook() {
//
//                @Override
//                public String branch() {
//                    return branchName;
//                }
//
//                @Override
//                public Event.Type type() {
//                    return Event.Type.MERGE;
//                }
//
//                @Override
//                public List<Event> caughtEvents() {
//                    return events;
//                }
//
//                @Override
//                public void onEvent(Event event) {
//                    events.add(event);
//                }
//
//            };
//            return webHook;
//        } catch (UnsupportedOperationException e) {
//            throw new UnsupportedOperationException();
//        }
//    }
//
//    public static WebHook commitToBranchWebHook(String branchName) {
//        try {
//            WebHook webHook = new WebHook() {
//
//                @Override
//                public String branch() {
//                    return branchName;
//                }
//
//                @Override
//                public Event.Type type() {
//                    return Event.Type.COMMIT;
//                }
//
//                @Override
//                public List<Event> caughtEvents() {
//                    System.out.println("events" + events);
//                    return events;
//                }
//
//                @Override
//                public void onEvent(Event event) {
//                    events.add(event);
//                    System.out.println("event" + event);
//                }
//
//            };
//            return webHook;
//        } catch (UnsupportedOperationException e) {
//            throw new UnsupportedOperationException();
//        }
//    }
//
//}
