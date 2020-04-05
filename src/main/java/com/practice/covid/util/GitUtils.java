package com.practice.covid.util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ProgressMonitor;

import java.io.File;
import java.util.Arrays;

public class GitUtils {
    public static Git checkoutOrGetLatest(final String repositoryName, final String branchName) {

            File localPath = new File("JGitTestRepository1", "");

            if (localPath.exists())
                return getLatest(localPath, repositoryName, branchName);
            else
                return checkout(localPath, repositoryName, branchName);

    }

    private static Git checkout(File localPath, String repositoryName, String branchName) {
        System.out.println("Cloning covid data repository: " + repositoryName);
        try (Git git = Git.cloneRepository()
                .setURI(repositoryName)
                .setDirectory(localPath)
                .setBranchesToClone(Arrays.asList(branchName))
                .setBranch(branchName)
                .setProgressMonitor(MONITOR)
                .call()) {
            System.out.println("Repository cloned successfully");
            return git;
        } catch (Exception e) {
            throw new RuntimeException("Could not checkout " + repositoryName, e);
        }
    }

    private static Git getLatest(File localPath, String repositoryName, String branchName) {
        System.out.println("Using existing repository: " + repositoryName);
        try (Git git = Git.open(localPath)) {
            System.out.println("Existing repository opened successfully");
            return git;
        } catch (Exception e) {
            throw new RuntimeException("Could not open " + repositoryName, e);
        }
    }

    private static final ProgressMonitor MONITOR = new ProgressMonitor() {
        @Override
        public void start(int totalTasks) {

        }

        @Override
        public void beginTask(String title, int totalWork) {

        }

        @Override
        public void update(int completed) {

        }

        @Override
        public void endTask() {

        }

        @Override
        public boolean isCancelled() {
            return false;
        }
    };
}
